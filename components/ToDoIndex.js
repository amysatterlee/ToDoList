import React from 'react';
import { StyleSheet, FlatList, Text } from 'react-native';

const ToDoIndex = ({ todos }) => {
  const keyExtractor = (item, _idx) => (item._id.toString())

  const renderToDo = ({item}) => {
    console.log(item);
    return <Text id={item._id}>{item.description}</Text>;
  };
  const render = () => {
    return <FlatList data={todos} renderItem={renderToDo} keyExtractor={keyExtractor}/>
  };
  return (
    <>{render()}</>
  );
};

export default ToDoIndex;
