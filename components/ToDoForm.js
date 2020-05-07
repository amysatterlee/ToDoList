import React, { useState } from 'react';
import { StyleSheet, View, TextInput, Button } from 'react-native';

const ToDoForm = ({ handleAdd }) => {
  const [value, setValue] = useState();
  const handleChange = (val) => {
    setValue(val);
  };
  const handlePress = () => {
    handleAdd(value);
  };
  return (
    <View>
      <TextInput onChangeText={handleChange}>{value}</TextInput>
      <Button onPress={handlePress} title="Add to List"/>
    </View>
  );
};

export default ToDoForm;
