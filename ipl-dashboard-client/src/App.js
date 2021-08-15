import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { Team } from './pages/Team';
import { Match } from './pages/Match';
import { Home } from './pages/Home';

function App() {
    return (
        <div className="App container">
          <Router>
            <Switch>
              <Route path="/" exact>
                <Home />
              </Route>
              <Route path="/teams/:teamName" exact>
                <Team />
              </Route>
              <Route path="/teams/:teamName/matches/:year" exact>
                <Match />
              </Route>
            </Switch>
          </Router>
        </div>
    );
}

export default App;
