import React, { useState, useEffect, useMemo, useRef } from "react";
import { useNavigate } from "react-router-dom";
import TutorialDataService from "../services/CountryService";
import { useTable } from "react-table";

const CountryList = (props) => {
  const [countries, setCountries] = useState([]);
  const [searchCc2, setSearchCc2] = useState("");
  const countriesRef = useRef();
  const navigate = useNavigate();

  countriesRef.current = countries;

  useEffect(() => {
    retrieveCountries();
  }, []);

  const onChangeSearchCc2 = (e) => {
    const searchCc2 = e.target.value;
    setSearchCc2(searchCc2);
  };

  const retrieveCountries = () => {
    TutorialDataService.getAll()
      .then((response) => {
        setCountries(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrieveCountries();
  };

  const process = () => {
    TutorialDataService.process()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const findByCc2 = () => {
    TutorialDataService.findByCc2(searchCc2)
      .then((response) => {
        setCountries(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const openCountry = (rowIndex) => {
    const cca2 = countriesRef.current[rowIndex].cca2;
    console.log('props');
    console.log(props);
    console.log('props.history');
    console.log(props.history);
    console.log(countriesRef);
    console.log(rowIndex);
    console.log(countriesRef.current[rowIndex]);
    console.log(countriesRef.current[rowIndex].cca2);
    navigate("/countries/" + cca2);
  };

  const columns = useMemo(
    () => [
      {
        Header: "CCA2",
        accessor: "cca2",
      },
      {
        Header: "CCA3",
        accessor: "cca3",
      },
      {
        Header: "Capital(s)",
        accessor: "capital",
      },
      {
        Header: "Independent",
        accessor: "independent",
        Cell: (props) => {
          return props.value ? "Independent" : "Dependent";
        },
      },
      {
        Header: "Actions",
        accessor: "actions",
        Cell: (props) => {
          const rowIdx = props.row.id;
          return (
            <div>
              <span onClick={() => openCountry(rowIdx)}>
                <i className="far fa-edit action mr-2"></i>
              </span>
            </div>
          );
        },
      },
    ],
    []
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data: countries,
  });

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by title"
            value={searchCc2}
            onChange={onChangeSearchCc2}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={findByCc2}
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-12 list">
        <table
          className="table table-striped table-bordered"
          {...getTableProps()}
        >
          <thead>
            {headerGroups.map((headerGroup) => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <th {...column.getHeaderProps()}>
                    {column.render("Header")}
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map((cell) => {
                    return (
                      <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                    );
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>

      <div className="col-md-8">
        <button className="btn btn-sm btn-danger" onClick={process}>
          Process
        </button>
      </div>
    </div>
  );
};

export default CountryList;
