import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";
import "./App.css";

import Country from "./components/Country";
import CountryList from "./components/CountryList";

function App() {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <a href="/countries" className="navbar-brand">
          proselyte
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/countries"} className="nav-link">
              Countries
            </Link>
          </li>
        </div>
      </nav>

      <div className="container mt-3">
        <Routes>
          <Route exact path="/" element={<CountryList />} />
          <Route exact path="/countries" element={<CountryList />} />
          <Route exact path="/countries/:id" element={<Country />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
