import React, { useEffect, useState } from 'react';
import connection from "../../axios_helper";
import classes from './UserList.module.css'; // Create a CSS module for styling

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await connection.get('/api/appusers/devel/findAll');
                setUsers(response.data);
            } catch (err) {
                setError('Failed to fetch users. Please try again later.');
            } finally {
                setLoading(false);
            }
        };

        fetchUsers();
    }, []);

    if (loading) {
        return <p>Loading users...</p>;
    }

    if (error) {
        return <p style={{ color: 'red' }}>{error}</p>;
    }

    return (
        <div className={classes.UserList}>
            <h2>List of Users</h2>
            <table className={classes.UserTable}>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nazwa</th>
                    <th>Przypisana Rola</th>
                    {/* Add more headers based on AppUser fields */}
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.userId}> {/* Adjust based on your AppUser ID field */}
                        <td>{user.userId}</td> {/* Adjust based on your AppUser fields */}
                        <td>{user.appUserName}</td> {/* Adjust based on your AppUser fields */}
                        <td>{user.role}</td> {/* Adjust based on your AppUser fields */}
                        {/* Add more fields based on AppUser properties */}
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;
