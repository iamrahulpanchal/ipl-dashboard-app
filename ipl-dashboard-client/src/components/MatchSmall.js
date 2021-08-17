import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmall = ({match, teamName}) => {

    if(!match) return null;

    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    return (
        <div className="MatchSmall">
            <b>vs <Link to={vsTeamLink}>{vsTeam}</Link></b><br />
            { 
                match.result === 'tie' ?
                <b>Match Tied</b> :
                <b>{match.matchWinner} won by {match.resultMargin} {match.result} </b>
            }
        </div>
    );
}