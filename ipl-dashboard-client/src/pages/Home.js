import { React, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export const Home = () => {
    
    const [ allTeams, setAllTeams ] = useState([]);

    useEffect(() => {
        const fetchTeams = async () => {
            const response = await fetch('http://localhost:8080');
            const data = await response.json();
            setAllTeams(data);
            console.log(data);
        };
        fetchTeams();
    }, []);

    const teamPage = "/teams/";

    return(
        <div className="Home">
            <h1>Teams List</h1>
            {allTeams.map(team => {
                return <Link to={teamPage + team}><h5>{team}</h5></Link>
            })}
        </div>
    );
}