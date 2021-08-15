import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetail = ({match, teamName}) => {
    
    if(!match) return null;

    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    return (
        <div className="MatchDetail">
            <hr />
            <h3>Latest Matches</h3>
            <h4>vs <Link to={vsTeamLink}>{vsTeam}</Link></h4>
            <p>{match.date}</p>
            <p>at {match.venue}, {match.city}</p>
            { match.result === 'tie' ? 
            <h4>Match Tied</h4> : <h4>{match.matchWinner} won by {match.resultMargin} {match.result} </h4>
            }
            <hr />
        </div>
    );
}