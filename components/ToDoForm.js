import React from 'react';
import { StyleSheet, View, TextInput, Button } from 'react-native';

const ToDoForm = ({ value, handleChange, handleAdd }) => {
  return (
    <View>
      <TextInput onChangeText={handleChange}>{value}</TextInput>
      <Button onPress={handleAdd} title="Add to List"/>
    </View>
  );
};

export default ToDoForm;
