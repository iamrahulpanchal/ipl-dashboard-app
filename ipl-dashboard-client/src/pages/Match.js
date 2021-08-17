import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Header } from '../components/Header';
import { MatchDetail } from '../components/MatchDetail';
import { NotFound } from './NotFound';

export const Match = () => {

    const [ matches, setMatches ] = useState([]);
    const [ statusCode, setStatusCode ] = useState([]);
    const [ years, setYears ] = useState([]);
    const [ loading, setLoading ] = useState(true);
    
    const { teamName, year } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const matchesResponse = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
            const matchesData = await matchesResponse.json();
            
            if(matchesData.length === 0){
                setStatusCode(500);
            } else {
                setStatusCode(200);
                const yearsResponse = await fetch(`http://localhost:8080/teams/${teamName}/years`);
                const yearsData = await yearsResponse.json();
                setYears(yearsData);
                console.log(yearsData);
            }

            setMatches(matchesData);
            setLoading(false);
            console.log(matchesData);
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
            <Link to="/"><Header /></Link>
            <h3>Match Page</h3>
            {years.map(year => {
                return <div key={year}><Link to={matchesByYearLink + year}><h5>{year}</h5></Link></div>
            })}
            {matches.map(match => {
                return <MatchDetail key={match.matchId} match={match} teamName={teamName} />
            })}
        </div>
    );
}