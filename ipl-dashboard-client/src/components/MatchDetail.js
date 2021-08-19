import { React } from 'react';
import { Link } from 'react-router-dom';
import './MatchDetail.css';

export const MatchDetail = ({match, teamName}) => {
    
    const vsTeam = match.team1 === teamName ? match.team2 : match.team1;
    const vsTeamLink = `/teams/${vsTeam}`;

    const applyClass = match.matchWinner === teamName ? "greenBorder" : "redBorder";

    return (
        <div className={applyClass}>
            <div className="row">

                <div className="col-md-6 col-lg-6 col-xl-6 col-sm-12 col-12">
                    <b className="vs-team-link">vs <Link to={vsTeamLink}>{vsTeam}</Link></b>
                    <p className="match-date">{match.date}</p>
                    <p className="match-venue-city">at {match.venue}, {match.city}</p>
                    { 
                        match.result === 'tie' ?
                        <b className="match-result">Match Tied</b> :
                        <b className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result} </b>
                    }
                </div>
                
                {
                    match.result === 'tie' ? "" :
                    <div className="col-md-6 col-lg-6 col-xl-6 col-sm-12 col-12 motm-umpires-flex">
                        <div className="motm-umpires-align">
                            <b className="motm-text">Man of the Match</b>
                            <p className="motm-data">{match.playerOfMatch}</p>
                            <p className="match-umpires">Umpires</p>
                            <p className="match-umpire12">{match.umpire1} & {match.umpire2}</p>
                        </div>
                    </div>
                }

            </div>
        </div>
    );
}