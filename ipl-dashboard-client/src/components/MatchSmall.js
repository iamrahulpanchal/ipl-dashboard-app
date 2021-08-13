import { React } from 'react';

export const MatchSmall = ({match}) => {
    return (
        <div className="MatchSmall">
            <p>{match.team1} vs {match.team2}</p>
        </div>
    );
}