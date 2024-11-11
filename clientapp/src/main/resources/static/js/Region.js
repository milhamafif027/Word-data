// console.log("Testing");

$(document).ready(() => {
    $("#tb-region").DataTable({
      ajax: {
        method: "GET",
        url: "/api/region",
        dataSrc: "",
      },
      columnDefs: [{ className: "text-center", targets: "_all" }],
      columns: [
        { data: "id" },
        { data: "name" },
        {
          data: null,
          render: (data) => {
            return /* html */ `
              <div class="d-flex gap-3 justify-content-center align-items-center">
                <button
                  type="button"
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#detail"
                  onclick="findById(${data.id})"
                >
                  Detail
                </button>
                <button
                  type="button"
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#update"
                  onclick="beforeUpdate(${data.id})"
                >
                  Update
                </button>
                <button
                  type="button"
                  class="btn btn-danger btn-sm"
                  onclick="deleteRegion(${data.id})"
                >
                  Delete
                </button>
              </div>
            `;
          },
        },
      ],
    });
  });
  $("#create-region").click((event) => {
    event.preventDefault();
    const valueName = $("#create-name").val();
    if (valueName === "" || valueName === null) {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Tolong isi semua input",
        showConfirmButton: false,
        timer: 1500,
      });
    } else {
      $.ajax({
        method: "POST",
        url: "/api/region",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify({
          name: valueName,
        }),
        success: (res) => {
          $("#create").modal("hide");
          $("#tb-region").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Region Baru Telah Disimpan",
            showConfirmButton: false,
            timer: 1500,
          });
          $("#create-name").val("");
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  });
  
  function findById(id) {
    // console.log(id);
    $.ajax({
      method: "GET",
      url: `/api/region/${id}`,
      dataType: "JSON",
      contentType: "application/json",
      success: (res) => {
        // console.log(res);
        $("#detail-id").val(res.id);
        $("#detail-name").val(res.name);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  function beforeUpdate(id) {
    $.ajax({
      method: "GET",
      url: `/api/region/${id}`,
      dataType: "JSON",
      contentType: "application/json",
      success: (res) => {
        $("#update-id").val(res.id);
        $("#update-name").val(res.name);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  $("#update-region").click((event) => {
    event.preventDefault();
    const valueName = $("#update-name").val();
    const valueId = $("#update-id").val();
    const updateData = {
      name: valueName,
    };
    if (valueName === "" || valueName === null) {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Tolong isi semua input",
        showConfirmButton: false,
        timer: 1500,
      });
    } else {
      $.ajax({
        method: "PUT",
        url: `/api/region/${valueId}`,
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(updateData),
        success: (res) => {
          $("#update").modal("hide");
          $("#tb-region").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Perubahan telah disimpan",
            showConfirmButton: false,
            timer: 1500,
          });
          $("#update-name").val("");
          $("#update-id").val("");
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  });
  function deleteRegion(id) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: "btn btn-success btn-sm ms-3",
        cancelButton: "btn btn-danger btn-sm",
      },
      buttonsStyling: false,
    });
    swalWithBootstrapButtons
      .fire({
        title: "Apa anda yakin ingin menghapus data ini?",
        text: "Data tidak dapat kembali setelah dihapus",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Ya",
        cancelButtonText: "Tidak",
        reverseButtons: true,
      })
      .then((result) => {
        if (result.isConfirmed) {
          $.ajax({
            method: "DELETE",
            url: `/api/region/${id}`,
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
              $("#tb-region").DataTable().ajax.reload();
            },
            error: (err) => {
              console.log(err);
            },
          });
          swalWithBootstrapButtons.fire({
            title: "Terhapus",
            text: "Data region telah terhapus",
            icon: "success",
          });
        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire({
            title: "Batal",
            text: "Region batal untuk dihapus",
            icon: "error",
          });
        }
      });
  }