import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import DbHelper from '../javaHooks/DbHelper';

const App: () => React$Node = () => {
  useEffect(() => { loadToDoList(); }, []);
  const [todos, setToDos] = useState(null);

  const addToList = (description) => {
    let dt = new Date();
    const item = {
      description: description,
      status: 'TO DO',
      created_at: dt.toString(),
      updated_at: dt.toString()
    }
    DbHelper.add(item, (res) => {
      console.log(`To Do added - ${res}`);
    });
  };

  const loadToDoList = () => {
    DbHelper.get((items) => {
      items.forEach((it) => { console.log(it); })
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
