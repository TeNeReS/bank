'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        getUsers().then((data) => {
            this.setState({users: data});
        });
    }

    render() {
        return (
            <UserList users={this.state.users}/>
        );
    }
}

class UserList extends React.Component{
    render() {
        var users = this.props.users.map(function(user, i) {
            return <User key={arguments[i]} user={user}/>
        });

        return (
            <table>
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Age</th>
                </tr>
                {users}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component{
    render() {
        var url = "/users/" + this.props.user.id.toString() + "/accounts";
        return (
            <tr>
                <td>{this.props.user.id}</td>
                <td><a href={url}>{this.props.user.name}</a></td>
                <td>{this.props.user.address}</td>
                <td>{this.props.user.age}</td>
            </tr>
        )
    }
}


function getUsers () {
    return fetch('/users')
        .then((response) => response.json());
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);