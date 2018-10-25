import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import combinedReducer from './reducers/combinedReducer'
import TestContainer from './containers/testContainer';

let initialState = {}
let store = createStore(combinedReducer, initialState);

ReactDOM.render(
    <Provider store={store}>
        {/* Insert container component here */}
        <TestContainer />
    </Provider>,
    document.getElementById('app')
);