import { React, useEffect, useState } from 'react';
import { TeamCard } from '../components/TeamCard';
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

    return(
        <div className="Home">
            
            <div className="container">

                <div className="row">
                    <h3 className="all-teams-header">IPL Mania</h3>
                    <hr className="hr-tag" />
                </div>

                <div className="row">
                    {allTeams.map(team => {
                        return (
                            <div key={team} className="col-md-6 col-lg-4 col-xl-4 col-sm-6 col-6 team-div">
                                <TeamCard team={team} />
                            </div>
                        );
                    })}
                </div>

            </div>
            
        </div>
    );
}