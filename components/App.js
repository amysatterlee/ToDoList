import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import ToDoIndex from './ToDoIndex';
import DbHelper from '../javaHooks/DbHelper';

const App: () => React$Node = () => {
  useEffect(() => { loadToDoList(); }, []);
  const [todos, setToDos] = useState(null);
  const [inputValue, setValue] = useState('');
  const [loading, setLoading] = useState(false);

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
    setLoading(true);
    DbHelper.get((items) => {
      setLoading(false);
      setToDos(items);
    });
  };

  const updateToDoStatus = (todo, status) => {
    console.log(`Updating to do ${todo._id} to ${status}`);
    let updated_at = (new Date()).toString();
    let item = {...todo, status, updated_at};
    // update state before saving to database to avoid the delay in rendering the toggle
    // if error updating state then show error and refresh list
    const items = todos.map(todo => {
      if (todo._id === item._id) {
        return item;
      }
      return todo;
    });
    setToDos(items);
    DbHelper.update(item, (res) => {
      if (res <= 0) {
        console.log(`DB Update result - ${res}`);
        loadToDoList();
      }
    });
  }

  return (
    <>
      <View style={styles.pageLayout}>
        <ToDoForm value={inputValue} handleChange={changeInputValue} handleAdd={addToList}/>
        <ToDoIndex todos={todos} updateToDoStatus={updateToDoStatus} loading={loading}/>
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
