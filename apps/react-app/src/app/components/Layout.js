import React, { Component } from "react";
import { Container } from "reactstrap";
import NavMenu from "./NavMenu";
import '../App.css';

export default class Layout extends Component {
  static displayName = Layout.name;

  render() {
    return (
      <div>
        <NavMenu />
        <Container
          style={{
            display:'flex',
            alignItems: 'center',
            justifyContent:'center',
            height: "90vh",
            width: "100vw"
          }}
        >
          {this.props.children}
        </Container>
      </div>
    );
  }
}
