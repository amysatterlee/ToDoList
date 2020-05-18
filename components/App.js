import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import ToDoIndex from './ToDoIndex';
import DbHelper from '../javaHooks/DbHelper';

const App: () => React$Node = () => {
  useEffect(() => { loadToDoList(); }, []);
  const [todos, setToDos] = useState(null);
  const [inputValue, setValue] = useState('');

  const changeInputValue = (val) => {
    setValue(val);
  };

  const addToList = () => {
    if (inputValue && inputValue.length > 0) {
      let dt = new Date();
      const item = {
        description: inputValue,
        status: 'active',
        created_at: dt.toString(),
        updated_at: dt.toString()
      }
      DbHelper.add(item, (res) => {
        setValue('');
        loadToDoList();
      });
    }
  };

  const loadToDoList = () => {
    DbHelper.get((items) => {
      setToDos(items);
    });
  };

  const updateToDoStatus = (todo, status) => {
    console.log(`Updating to do ${todo._id} to ${status}`);
    let dt = new Date();
    let item = {
      _id: todo._id,
      status: status,
      updated_at: dt.toString()
    };
    DbHelper.update(item, (res) => {
      if (res > 0) {
        loadToDoList();
      }
    });
  }

  return (
    <>
      <View style={styles.pageLayout}>
        <ToDoForm value={inputValue} handleChange={changeInputValue} handleAdd={addToList}/>
        <ToDoIndex todos={todos} updateToDoStatus={updateToDoStatus}/>
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
