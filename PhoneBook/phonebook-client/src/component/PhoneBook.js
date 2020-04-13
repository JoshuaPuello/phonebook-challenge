import React, { Component } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Panel } from "primereact/panel";
import { Menubar } from "primereact/menubar";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import ContactDialog from "./CreateContactDialog";

class PhoneBook extends Component {
  constructor(props) {
    super(props);

    this.state = {
      inputValue: "",
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
      {
        label: "List All",
        icon: "pi pi-list",
        command: () => {
          this.props.handleOnListAll();
        },
      },
    ];
  }

  hideDialog = () => {
    this.setState({ visible: false });
  };

  render() {
    return (
      <div>
        <Menubar model={this.items}>
          <InputText
            value={this.props.inputValue}
            onChange={this.props.handleInputChange}
            placeholder="Search"
            type="text"
          />
          <Button
            onClick={this.props.handleSearchClick}
            icon="pi pi-search"
            style={{ marginLeft: 4 }}
          />
        </Menubar>

        <br />

        <Panel header="Phone Book">
          <DataTable
            emptyMessage="No records found"
            selectionMode="single"
            className="p-datatable-responsive p-datatable"
            value={this.props.contacts}
          >
            <Column field="firstName" header="First name"></Column>
            <Column field="lastName" header="Last name"></Column>
            <Column field="address" header="Address"></Column>
            <Column field="email" header="Email"></Column>
            <Column field="phoneNumber" header="Phone number"></Column>
          </DataTable>
        </Panel>

        <ContactDialog
          visible={this.state.visible}
          contact={this.props.contact}
          hideDialog={this.hideDialog}
          handleOnSave={this.props.handleOnSave}
          handleFieldChange={this.props.handleFieldChange}
        />
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

export default PhoneBook;
