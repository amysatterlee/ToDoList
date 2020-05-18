import React from 'react';
import { StyleSheet, FlatList, Text } from 'react-native';
import ToDoItem from './ToDoItem';

const ToDoIndex = ({ todos, updateToDoStatus }) => {
  const keyExtractor = (item, _idx) => (item._id.toString())

  const renderToDo = ({item}) => {
    return <ToDoItem item={item} updateStatus={updateToDoStatus}/>;
  };

  return (
    <><FlatList data={todos} renderItem={renderToDo} keyExtractor={keyExtractor}/></>
  );
};

export default ToDoIndex;
