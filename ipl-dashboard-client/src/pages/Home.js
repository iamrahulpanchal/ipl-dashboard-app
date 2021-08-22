import { React, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Header } from '../components/Header';

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
            {/* <Link to="/"><Header /></Link> */}
            <h3>Teams</h3>
            {allTeams.map(team => {
                return (
                    <div key={team}>
                        <Link to={teamPage + team}>
                            <h5>{team}</h5>
                        </Link>
                    </div>
                );
            })}
        </div>
    );
}