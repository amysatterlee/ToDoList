import React from 'react';
import { StyleSheet, View } from 'react-native';
import ToDoForm from './ToDoForm';
import ToastExample from './ToastExample';

const App: () => React$Node = () => {
  const addToList = (item) => {
    ToastExample.show(`you added ${item}`, "SHORT");
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
