import React, { useState, useEffect } from "react";
import TutorialDataService from "../services/CountryService"

const Country = props => {
  const initialTutorialState = {
    cca2: "",
    cca3: "",
    region: "",
    capital: [],
    subregion: "",
    area: 0,
    population: 0,
    independent: true
  };
  const [currentTutorial, setCurrentTutorial] = useState(initialTutorialState);
  const [message, setMessage] = useState("");
  useEffect(() => {
    console.log('currentTutorial');
    console.log(currentTutorial);
    retrieveCountry();
  }, []);

  const retrieveCountry = () => {
    TutorialDataService.findByCc2('MD')
      .then((response) => {
        setCurrentTutorial(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handleInputChange = event => {

    const { name, value } = event.target;
    console.log('currentTutorial');
    console.log(currentTutorial);
    setCurrentTutorial({ ...currentTutorial, [name]: value });
  };

  return (
    <div>
      {currentTutorial ? (
        <div className="card">
          <h4>Country</h4>
          <div  className="card-body">
            <div>
              <label htmlFor="title">CCA2</label>
              <input
                disabled={true}
                type="text"
                className="form-control"
                id="cca2"
                name="cca2"
                value={currentTutorial.cca2}
                onChange={handleInputChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="region">Region</label>
              <input
                type="text"
                className="form-control"
                id="region"
                name="region"
                value={currentTutorial.region}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>
                <strong>Status:</strong>
              </label>
              {currentTutorial.independent ? "Independent" : "Not independent"}
            </div>
          </div>

          <p>{message}</p>
        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Country...</p>
        </div>
      )}
    </div>
  );
};

export default Country;
