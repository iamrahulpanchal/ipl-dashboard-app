import { React } from 'react';
import { Link } from 'react-router-dom';

export const TeamCard = ({team}) => {

    const teamPage = "/teams/";
    
    return (
        <div className="inner-team-div">
            <Link to={teamPage + team}>
                <h5 className="team-name">{team}</h5>
            </Link>
        </div>
    );
};