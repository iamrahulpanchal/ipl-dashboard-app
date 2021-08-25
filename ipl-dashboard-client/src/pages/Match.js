import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { NotFound } from './NotFound';
import './Match.css';

export const Match = () => {

    const [ matches, setMatches ] = useState([]);
    const [ statusCode, setStatusCode ] = useState([]);
    const [ years, setYears ] = useState([]);
    const [ loading, setLoading ] = useState(true);
    
    const { teamName, year } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const matchesResponse = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}/matches?year=${year}`);
            const matchesData = await matchesResponse.json();
            
            if(matchesData.length === 0){
                setStatusCode(500);
            } else {
                setStatusCode(200);
                const yearsResponse = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}/years`);
                const yearsData = await yearsResponse.json();
                setYears(yearsData);
            }

            setMatches(matchesData);
            setLoading(false);
        };
        fetchMatches();
    }, [teamName, year]);

    if(statusCode === 500){
        return <NotFound />
    }

    const matchesByYearLink = `/teams/${teamName}/matches/`;

    return (
        loading ? <p></p> : 

        <div className="Match">

            <div className="container">

                <div className="row">
                    <div className="col-lg-2 col-xl-2 col-md-2 col-sm-12 col-12 years-row">
                        <h5 className="select-year">Select Year</h5>
                        {years.map(year => {
                            return <div key={year}><Link to={matchesByYearLink + year}><h6>{year}</h6></Link></div>
                        })}
                    </div>
                    
                    <div className="col-lg-10 col-xl-10 col-md-10 col-sm-12 col-12 match-page-list">
                        <h1>{teamName}</h1>
                        {matches.map(match => {
                            return <MatchDetail key={match.matchId} match={match} teamName={teamName} />
                        })}
                    </div>
                </div>
                
            </div>

        </div>
    );
}