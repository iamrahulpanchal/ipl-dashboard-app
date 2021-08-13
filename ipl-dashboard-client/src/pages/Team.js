import { React, useEffect, useState } from 'react';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';

export const Team = () => {

    const [team, setTeam] = useState({
        first4Matches: []
    });

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch('http://localhost:8080/team/Mumbai Indians');
            const data = await response.json();
            setTeam(data);
        };
        fetchMatches();
    }, []);

    return (
        <div className="Team">
            <h1>{team.teamName}</h1>
            <MatchDetail match={team.first4Matches[0]}/>
            {team.first4Matches.slice(1).map(match => {
                return <MatchSmall match={match} />
            })}
        </div>
    );
}