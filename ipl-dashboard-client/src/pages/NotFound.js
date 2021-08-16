import { React } from 'react';
import { Link } from 'react-router-dom';
import { Header } from '../components/Header';

export const NotFound = () => {
    return (
        <div>
            <Link to="/"><Header /></Link>
            <h3>Something went wrong</h3>
        </div>
    );
}