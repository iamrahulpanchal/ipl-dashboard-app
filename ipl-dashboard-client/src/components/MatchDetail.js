import { React } from 'react';

export const MatchDetail = ({match}) => {
    if(!match) return null;
    return (
        <div className="MatchDetail">
            <h3>Latest Matches</h3>
            <h4>Match Details</h4>
            <b>{match.team1} vs {match.team2}</b>
        </div>
    );
}