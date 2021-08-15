import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { NotFound } from './NotFound';

export const Match = () => {

    const [ matches, setMatches ] = useState([]);
    const [ statusCode, setStatusCode ] = useState([]);
    const [ years, setYears ] = useState([]);
    
    const { teamName, year } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const matchesResponse = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
            const matchesData = await matchesResponse.json();
            const yearsResponse = await fetch(`http://localhost:8080/teams/${teamName}/years`);
            const yearsData = await yearsResponse.json();
            if(matchesData.length === 0){
                setStatusCode(500);
            } else {
                setStatusCode(200);
            }
            console.log(matchesData);
            console.log(yearsData);
            setMatches(matchesData);
            setYears(yearsData);
        };
        fetchMatches();
    }, [teamName, year]);

    if(statusCode === 500){
        return <NotFound />
    }

    return (
        <div className="Match">
            <Link to="/"><h1>HOME PAGE</h1></Link>
            {statusCode === 200 ? <h1>Match Page</h1> : <p></p>}
            {years.map(year => {
                return <h5>{year}</h5>
            })}
            {matches.map(match => {
                return <MatchDetail key={match.matchId} match={match} teamName={teamName} />
            })}
        </div>
    );
}