import React, { Component } from "react";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";

class ContactDialog extends Component {
  constructor(props) {
    super(props);

    this.state = {};

    this.footer = (
      <div>
        <Button
          label="Save"
          icon="pi pi-check"
          onClick={() => {
            this.props.handleOnSave();
            this.props.hideDialog();
          }}
        />
      </div>
    );
  }

  render() {
    return (
      <div>
        <Dialog
          header="Crear contact"
          footer={this.footer}
          visible={this.props.visible}
          style={{ width: "400" }}
          modal={true}
          onHide={this.props.hideDialog}
        >
          <form id="contact-form">
            <span className="p-float-label">
              <InputText
                value={this.props.contact.firstName}
                onChange={(e) => {
                  let value = e.target.value;
                  this.props.handleFieldChange("firstName", value);
                }}
              />
              <label htmlFor="firstName">First name</label>
            </span>
            <span className="p-float-label">
              <InputText
                value={this.props.contact.lastName}
                onChange={(e) => {
                  let value = e.target.value;
                  this.props.handleFieldChange("lastName", value);
                }}
              />
              <label htmlFor="lastName">Last name</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.props.contact.address}
                onChange={(e) => {
                  let value = e.target.value;
                  this.props.handleFieldChange("address", value);
                }}
              />
              <label htmlFor="address">Address</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.props.contact.email}
                onChange={(e) => {
                  let value = e.target.value;
                  this.props.handleFieldChange("email", value);
                }}
              />
              <label htmlFor="email">Email</label>
            </span>

            <span className="p-float-label">
              <InputText
                value={this.props.contact.phoneNumber}
                onChange={(e) => {
                  let value = e.target.value;
                  this.props.handleFieldChange("phoneNumber", value);
                }}
              />
              <label htmlFor="phoneNumber">Phone number</label>
            </span>
          </form>
        </Dialog>
      </div>
    );
  }
}

export default ContactDialog;
