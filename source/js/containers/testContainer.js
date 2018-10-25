import { connect } from 'react-redux'

import { constants } from '../constants/actionTypes.js'
import TestComponent from '../components/testComponent.js'

const mapStateToProps = (state, ownProps) => {
    return {}
}

const mapDispatchToProps = (dispatch) => {
    return {}
}

const TestContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(TestComponent)

export default TestContainer