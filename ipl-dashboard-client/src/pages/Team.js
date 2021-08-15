import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';
import { NotFound } from './NotFound';

export const Team = () => {

    const [team, setTeam] = useState({
        first4Matches: []
    });

    const [statusCode, setStatusCode] = useState({});

    const { teamName } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/teams/${teamName}`);
            setStatusCode(response.status);
            const data = await response.json();
            setTeam(data);
        };
        fetchMatches();
    }, [teamName]);

    if(statusCode === 500){
        return <NotFound />
    }

    return (
        <div className="Team">
            <h1>{team.teamName}</h1>
            <MatchDetail match={team.first4Matches[0]} teamName={teamName}/>
            {team.first4Matches.slice(1).map(match => {
                return <MatchSmall key={match.matchId} match={match}  teamName={teamName}/>
            })}
        </div>
    );
}