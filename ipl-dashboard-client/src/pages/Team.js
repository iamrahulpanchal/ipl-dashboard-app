import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Header } from '../components/Header';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';
import { NotFound } from './NotFound';

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
        <div className="Team">
            <Link to="/"><Header /></Link>
            <h3>{team.teamName}</h3>
            <hr />
            <h4>Latest Matches</h4>
            <hr />
            <MatchDetail match={team.first4Matches[0]} teamName={teamName}/>
            {team.first4Matches.slice(1).map(match => {
                return <MatchSmall key={match.matchId} match={match}  teamName={teamName}/>
            })}
            <hr />
            <Link to={moreMatches}>More Matches</Link>
        </div>
    );
}