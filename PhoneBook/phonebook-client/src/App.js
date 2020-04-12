import React, { Component } from "react";
import "./App.css";
import ContactService from "./service/ContactService";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Panel } from "primereact/panel";
import { Menubar } from "primereact/menubar";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Growl } from "primereact/growl";

import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

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
      visible: false,
    };
    this.items = [
      {
        label: "Create",
        icon: "pi pi-fw pi-plus",
        command: () => {
          this.showDialog();
        },
      },
    ];
    this.contactService = new ContactService();
    this.save = this.save.bind(this);
    this.footer = (
      <div>
        <Button label="Save" icon="pi pi-check" onClick={this.save} />
      </div>
    );
  }

  componentDidMount() {
    this.contactService.getAll().then((data) => {
      this.setState({ contacts: data });
    });
  }

  save() {
    this.contactService.save(this.state.contact).then((data) => {
      this.setState({
        visible: false,
        contact: {
          firstName: "",
          lastName: "",
          address: "",
          email: "",
          phoneNumber: "",
        },
      });
      this.growl.show({
        severity: "success",
        summary: "Success!",
        detail: "Contact submitted",
      });
      this.contactService.getAll().then((data) => {
        this.setState({ contacts: data });
      });
    });
  }

  render() {
    return (
      <div style={{ margin: "20px auto 0", width: "80%" }}>
        <Menubar model={this.items} />
        <br />
        <Panel header="Phone Book">
          <DataTable className="p-datatable-responsive p-datatable" value={this.state.contacts}>
            <Column field="firstName" header="First name"></Column>
            <Column field="lastName" header="Last name"></Column>
            <Column field="address" header="Address"></Column>
            <Column field="email" header="Email"></Column>
            <Column field="phoneNumber" header="Phone number"></Column>
          </DataTable>
        </Panel>

        <Dialog
          header="Crear contact"
          footer={this.footer}
          visible={this.state.visible}
          style={{ width: "400" }}
          modal={true}
          onHide={() => this.setState({ visible: false })}
        >
          <form id="contact-form">
            <span className="p-float-label">
              <InputText
                value={this.state.contact.firstName}
                onChange={(e) => {
                  let value = e.target.value;
                  this.setState((prevState) => {
                    let contact = Object.assign({}, prevState.contact);
                    contact.firstName = value;
                    return { contact };
                  });
                }}
              />
              <label htmlFor="firstName">First name</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.state.contact.lastName}
                onChange={(e) => {
                  let value = e.target.value;
                  this.setState((prevState) => {
                    let contact = Object.assign({}, prevState.contact);
                    contact.lastName = value;
                    return { contact };
                  });
                }}
              />
              <label htmlFor="lastName">Last name</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.state.contact.address}
                onChange={(e) => {
                  let value = e.target.value;
                  this.setState((prevState) => {
                    let contact = Object.assign({}, prevState.contact);
                    contact.address = value;
                    return { contact };
                  });
                }}
              />
              <label htmlFor="address">Address</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.state.contact.email}
                onChange={(e) => {
                  let value = e.target.value;
                  this.setState((prevState) => {
                    let contact = Object.assign({}, prevState.contact);
                    contact.email = value;
                    return { contact };
                  });
                }}
              />
              <label htmlFor="email">Email</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.state.contact.phoneNumber}
                onChange={(e) => {
                  let value = e.target.value;
                  this.setState((prevState) => {
                    let contact = Object.assign({}, prevState.contact);
                    contact.phoneNumber = value;
                    return { contact };
                  });
                }}
              />
              <label htmlFor="phoneNumber">Phone number</label>
            </span>
          </form>
        </Dialog>
        <Growl ref={(el) => (this.growl = el)} />
      </div>
    );
  }

  showDialog() {
    this.setState({
      visible: true,
      contact: {
        firstName: "",
        lastName: "",
        address: "",
        email: "",
        phoneNumber: "",
      },
    });
  }
}
