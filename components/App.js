import React from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import ToastExample from '../javaHooks/ToastExample';
import DbHelper from '../javaHooks/DbHelper';

const App: () => React$Node = () => {
  const addToList = (item) => {
    DbHelper.get("this is the DB Module now", ToastExample.SHORT);
  }
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
