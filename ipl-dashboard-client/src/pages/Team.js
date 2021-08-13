import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';

export const Team = () => {

    const [team, setTeam] = useState({
        first4Matches: []
    });

    const { teamName } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/team/${teamName}`);
            const data = await response.json();
            setTeam(data);
            console.log(data);
        };
        fetchMatches();
    }, [teamName]);

    if(!team.teamName) {
        return <h1>Team Not Found!</h1>
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