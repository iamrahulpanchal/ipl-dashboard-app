import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmall = ({match, teamName}) => {

    if(!match) return null;

    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    return (
        <div className="MatchSmall">
            <h4>vs <Link to={vsTeamLink}>{vsTeam}</Link></h4>
            <h6>{match.matchWinner} won by {match.resultMargin} {match.result} </h6>
        </div>
    );
}