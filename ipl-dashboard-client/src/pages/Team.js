import { React } from 'react';
import { MatchDetail } from '../components/MatchDetail';
import { MatchSmall } from '../components/MatchSmall';

export const Team = () => {
    return (
        <div className="Team">
        <h1>Royal Challengers Bangalore</h1>
        <MatchDetail />
        <MatchSmall />
        </div>
    );
}