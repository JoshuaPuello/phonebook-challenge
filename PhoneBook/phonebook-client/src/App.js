import React, { Component } from "react";
import { Growl } from "primereact/growl";
import ContactService from "./service/ContactService";
import PhoneBook from "./component/PhoneBook";

import "./App.css";
import "primeicons/primeicons.css";
import "primereact/resources/primereact.min.css";
import "primereact/resources/themes/nova-light/theme.css";

export default class App extends Component {
  constructor() {
    super();
    this.state = {
      contact: {
        firstName: "",
        lastName: "",
        address: "",
        email: "",
        phoneNumber: "",
      },
      inputValue: "",
    };
    this.contactService = new ContactService();
    this.saveContact = this.saveContact.bind(this);
    this.getAllContacts = this.getAllContacts.bind(this);
    this.getContactsByText = this.getContactsByText.bind(this);
  }

  componentDidMount() {
    this.getAllContacts();
  }

  getAllContacts() {
    this.contactService.getAll().then((data) => {
      this.setState({ contacts: data });
    });
  }

  saveContact() {
    this.contactService.save(this.state.contact).then((data) => {
      this.showSuccess();
      this.clearContact();
      this.getAllContacts();
    }).catch((error) => {
      if (error.response) {
        this.showError(error.response.data.error);
      }
    });
  }

  showSuccess() {
    this.growl.show({
      severity: "success",
      summary: "Success!",
      detail: "Contact submitted",
    });
  }

  showError(error) {
    this.growl.show({
      sticky: true,
      severity: "error",
      summary: "Error message!",
      detail: error,
    });
  }

  getContactsByText(text) {
    this.contactService.getByText(text).then((data) => {
      this.setState({
        contacts: data,
        inputValue: "",
      });
    });
  };

  handleSearchClick = () => {
    this.getContactsByText(this.state.inputValue);
  };

  handleInputChange = (e) => {
    this.setState({
      inputValue: e.target.value,
    });
  };

  handleFieldChange = (field, value) => {
    this.setState((prevState) => {
      let contact = Object.assign({}, prevState.contact);
      contact[field] = value;
      return { contact };
    });
  };

  clearContact = () => {
    this.setState({
      contact: {
        firstName: "",
        lastName: "",
        address: "",
        email: "",
        phoneNumber: "",
      },
    });
  }

  render() {
    return (
      <div style={{ margin: "20px auto 0", width: "80%" }}>
        <PhoneBook
          contact={this.state.contact}
          contacts={this.state.contacts}
          inputValue={this.state.inputValue}
          handleSearchClick={this.handleSearchClick}
          handleInputChange={this.handleInputChange}
          handleFieldChange={this.handleFieldChange}
          handleOnListAll={this.getAllContacts}
          handleOnSave={this.saveContact}
        />
        <Growl ref={(el) => (this.growl = el)} />
      </div>
    );
  }
}
