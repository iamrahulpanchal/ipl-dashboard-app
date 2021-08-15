import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';
import { NotFound } from './NotFound';

export const Team = () => {

    const [team, setTeam] = useState({
        first4Matches: []
    });

    const [years, setYears] = useState([]);

    const [statusCode, setStatusCode] = useState({});

    const { teamName } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/teams/${teamName}`);
            const yearsResponse = await fetch(`http://localhost:8080/teams/${teamName}/years`);
            const yearsData = await yearsResponse.json();
            setStatusCode(response.status);
            const data = await response.json();
            setTeam(data);
            setYears(yearsData);
            console.log(data);
        };
        fetchMatches();
    }, [teamName]);

    if(statusCode === 500){
        return <NotFound />
    }

    const maxYear = years[0];

    const moreMatches = `/teams/${teamName}/matches/${maxYear}`;
    
    return (
        <div className="Team">
            <Link to="/"><h1>HOME PAGE</h1></Link>
            <h1>{team.teamName}</h1>
            <MatchDetail match={team.first4Matches[0]} teamName={teamName}/>
            {team.first4Matches.slice(1).map(match => {
                return <MatchSmall key={match.matchId} match={match}  teamName={teamName}/>
            })}
            <hr />
            <Link to={moreMatches}>More Matches</Link>
        </div>
    );
}