import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetail = ({match, teamName}) => {
    
    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    return (
        <div className="MatchDetail">
            <h5>vs <Link to={vsTeamLink}>{vsTeam}</Link></h5>
            <p>{match.date}</p>
            <p>at {match.venue}, {match.city}</p>
            { 
                match.result === 'tie' ?
                <h5>Match Tied</h5> :
                <h5>{match.matchWinner} won by {match.resultMargin} {match.result} </h5>
            }
            <hr />
        </div>
    );
}