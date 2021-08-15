import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetail } from '../components/MatchDetail';
import { NotFound } from './NotFound';

export const Match = () => {

    const [matches, setMatches] = useState({
        matchesList: [1],
        yearsList: []
    });
    
    const { teamName, year } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
            const data = await response.json();
            console.log(data);
            setMatches(data);
        };
        fetchMatches();
    }, [teamName, year]);

    if(matches.matchesList.length === 0){
        return <NotFound />
    }

    return (
        <div className="Match">
            <h1>Match Page</h1>
            {matches.yearsList.map(year => <div><b>{year}</b><br /></div>)}
            {matches.matchesList.map(match => {
                return <MatchDetail key={match.matchId} match={match} teamName={teamName} />
            })}
        </div>
    );
}