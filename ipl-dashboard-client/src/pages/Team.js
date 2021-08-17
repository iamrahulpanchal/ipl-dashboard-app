import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Header } from '../components/Header';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';
import { NotFound } from './NotFound';
import './Team.css';

export const Team = () => {

    const [ team, setTeam ] = useState([]);
    const [ statusCode, setStatusCode ] = useState();
    const [ loading, setLoading ] = useState(true);

    const { teamName } = useParams();

    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8080/teams/${teamName}`);
            const data = await response.json();
            setStatusCode(response.status);
            setTeam(data);
            setLoading(false);
            console.log(data);
        };
        fetchMatches();
    }, [teamName]);

    if(statusCode === 500){
        return <NotFound />
    }

    const maxYear = loading ? "" : team.first4Matches[0].date.substring(0, 4);
    const moreMatches = `/teams/${teamName}/matches/${maxYear}`;
    
    return (
        loading ? <p></p> :
        <div className="Team container">
            <Link to="/"><Header /></Link>
            
            <div className="row teamNameRow">
                <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h1>{team.teamName}</h1>
                </div>
                <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <p>Total Matches: {team.totalMatches}</p>
                    <p>Wins: {team.wins}, Losses: {team.losses}, Tied: {team.ties}, No Result: {team.noResult}</p>
                </div>
            </div>

            <div className="row latestMatchesRow">
                <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h4>Latest Matches</h4>
                </div>
            </div>
            
            <div className="row matchDetailRow">
                <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <MatchDetail match={team.first4Matches[0]} teamName={teamName}/>
                </div>
            </div>
            
            <div className="row matchSmallRow">
                {team.first4Matches.slice(1).map(match => {
                    return (
                        <div key={match.matchId} className="col-xl-3 col-lg-3 col-md-6 col-sm-6 col-12 testDetail">
                            <MatchSmall match={match} teamName={teamName}/>
                        </div>
                    );
                })}
                <div className="col-xl-3 col-lg-3 col-md-6 col-sm-6 col-12 testMore">
                    <Link to={moreMatches}>More Matches</Link>
                </div>
            </div>

        </div>
    );
}