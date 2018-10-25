import { combineReducers } from 'redux'
import { testReducer } from './testReducer.js';

const combinedReducer = combineReducers({
    testReducer
})

export default combinedReducer