import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import DbHelper from '../javaHooks/DbHelper';

const App: () => React$Node = () => {
  useEffect(() => { loadToDoList(); }, []);
  const [todos, setToDos] = useState(null);

  const addToList = (item) => {
    DbHelper.add(item);
  };

  const loadToDoList = () => {
    DbHelper.get((items) => {
      setToDos(items);
    });
  };

  return (
    <>
      <View style={styles.pageLayout}>
        <ToDoForm handleAdd={addToList}/>
      </View>
    </>
  );
};

const styles = StyleSheet.create({
  pageLayout: {
    margin: 10
  }
})

export default App;
