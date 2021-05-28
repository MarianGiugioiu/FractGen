import React, { Component } from "react";
import { Container } from "reactstrap";
import NavMenu from "./NavMenu";
import '../App.css';

import backgroundImage from "../images/background.jpg";

export default class Layout extends Component {
  static displayName = Layout.name;

  render() {
    return (
      <div style={{
        background:`url(${backgroundImage})`,
        height:"100vh",
        width:"100vw"
      }} className="backgroundDiv">
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
