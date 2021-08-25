import { React, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

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
            
            <div className="container">

                <div className="row">
                    <h3 className="all-teams-header">IPL Dashboard App</h3>
                    <hr className="hr-tag" />
                </div>

                <div className="row">
                    {allTeams.map(team => {
                        return (
                            <div key={team} className="col-md-6 col-lg-4 col-xl-4 col-sm-6 col-6 team-div">
                                <div className="inner-team-div">
                                    <Link to={teamPage + team}>
                                        <h5 className="team-name">{team}</h5>
                                    </Link>
                                </div>
                            </div>
                        );
                    })}
                </div>

            </div>
            
        </div>
    );
}