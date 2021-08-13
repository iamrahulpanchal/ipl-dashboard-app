import './App.css';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import { Team } from './pages/Team';

function App() {
    return (
        <div className="App container">
          <Router>
            <Route path="/" exact="true">
              <h1>Home Page</h1>
            </Route>
            <Route path="/teams/:teamName" exact="true">
              <Team />
            </Route>
          </Router>
        </div>
    );
}

export default App;
