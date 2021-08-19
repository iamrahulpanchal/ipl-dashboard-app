import { React } from 'react';
import { Link } from 'react-router-dom';
import './MatchSmall.css';

export const MatchSmall = ({match, teamName}) => {

    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    const applyClass = match.matchWinner === teamName ? "greenBorder" : "redBorder";

    return (
        <div className={applyClass}>
            <b>vs <Link to={vsTeamLink}>{vsTeam}</Link></b><br />
            { 
                match.result === 'tie' ?
                <b>Match Tied</b> :
                <p className="match-small-result">{match.matchWinner} won by {match.resultMargin} {match.result}</p>
            }
        </div>
    );
}