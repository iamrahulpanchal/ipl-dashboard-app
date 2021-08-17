import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetail = ({match, teamName}) => {
    
    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    return (
        <div className="MatchDetail">
            <b>vs <Link to={vsTeamLink}>{vsTeam}</Link></b><br />
            <p>{match.date}</p>
            <p>at {match.venue}, {match.city}</p>
            { 
                match.result === 'tie' ?
                <b>Match Tied</b> :
                <b>{match.matchWinner} won by {match.resultMargin} {match.result} </b>
            }
        </div>
    );
}