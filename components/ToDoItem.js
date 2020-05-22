import React from 'react';
import { StyleSheet, View, Text, Switch } from 'react-native';

const ToDoItem = ({item, updateStatus}) => {
  const determineTextStyle = () => {
    if (item.status === 'in progress') { return styles.textInProgress }
    return styles.text;
  }
  const switchValueChange = (val) => {
    if (val) {
      updateStatus(item, 'in progress');
    }
  }
  return (
    <View id={item._id} style={styles.view}>
      <Text style={determineTextStyle()}>{item.description}</Text>
      <View style={styles.toggleView} >
        <Switch
          style={styles.toggle}
          onValueChange={switchValueChange}
          value={item.status === 'in progress'}
          trackColor={{true: 'blue'}}
          thumbColor={item.status === 'in progress' ? 'blue' : null}
        />
        <Text style={styles.toggleText}>{item.status === 'in progress' ? 'In Progress' : null}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  view: {
    padding: 10,
    paddingLeft: 20,
    paddingRight: 20,
    borderBottomColor: 'grey',
    borderBottomWidth: StyleSheet.hairlineWidth,
    flexDirection: 'row'
  },
  text: {
    fontSize: 20,
    color: 'black',
    flex: 2
  },
  textInProgress: {
    fontSize: 20,
    color: 'blue',
    flex: 2
  },
  toggleView: {
    flex: 1,
    flexDirection: 'column',
    textAlign: 'center'
  },
  toggleText: {
    textAlign: 'center'
  }
})
export default ToDoItem;
