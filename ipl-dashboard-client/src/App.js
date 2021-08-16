import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { Team } from './pages/Team';
import { Match } from './pages/Match';
import { Home } from './pages/Home';
import { NotFound } from './pages/NotFound';

function App() {
    return (
        <div className="App container">
          <Router>
            <Switch>
              <Route path="/" component={Home} exact />
              <Route path="/teams/:teamName" component={Team} exact />
              <Route path="/teams/:teamName/matches/:year" component={Match} exact />
              <Route path="*" component={NotFound} />
            </Switch>
          </Router>
        </div>
    );
}

export default App;
