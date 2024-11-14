$(document).ready(() => {
    // Inisialisasi DataTable
    $("#tb-country").DataTable({
        ajax: {
            method: "GET",
            url: "/api/country",
            dataSrc: "",
        },
        columnDefs: [{
            className: "text-center",
            targets: "_all"
        }],
        columns: [{
                data: "id"
            },
            {
                data: "name"
            },
            {
                data: "code"
            },
            {
                data: "region.name"
            },
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
                                onclick="deleteCountry(${data.id})"
                            >
                                Delete
                            </button>
                        </div>
                    `;
                },
            },
        ],
    });
    // Fetch regions for dropdown
    $.ajax({
        url: '/api/region', // Endpoint to get regions
        method: 'GET',
        success: function (regions) {
            // Clear previous options
            $("#regionSelect").empty().append('<option value="" hidden>Choose your Region</option>');
            regions.forEach(region => {
                $("#regionSelect").append(new Option(region.name, region.id));
            });
        },
        error: function (xhr, status, error) {
            console.error('Error fetching regions:', error);
            Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'Failed to load regions.'
            });
        }
    });

    // Handle form submission for creating a country
    $("#create-country").click(() => {
        const countryName = $("#create-name").val().trim(); // Nama negara
        const countryCode = $("#create-code").val().trim(); // Kode negara
        const countryRegionId = $("#regionSelect").val(); // ID region

        // Validate form fields
        if (countryName && countryCode && countryRegionId) {
            $.ajax({
                url: '/api/country',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    name: countryName,
                    code: countryCode,
                    region: {
                        id: countryRegionId
                    }
                }),
                success: function () {
                    $("#create").modal('hide');
                    $("#createCountryForm")[0].reset(); // Reset form
                    Swal.fire({
                        icon: 'success',
                        title: 'Country Created!',
                        text: 'The country has been added successfully!'
                    }).then(() => {
                        location.reload(); // Refresh halaman
                    });
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error!',
                        text: 'There was an error creating the country.'
                    });
                    console.error('Error creating country:', error);
                }
            });
        } else {
            Swal.fire({
                icon: 'warning',
                title: 'Warning!',
                text: 'Please fill in all fields.'
            });
        }
    });

    // Fetch regions for the update dropdown (same as create)
$.ajax({
    url: '/api/region', // Endpoint untuk mendapatkan daftar region
    method: 'GET',
    success: function(regions) {
        // Clear previous options and add new options
        $("#update-regionSelect").empty().append('<option value="" hidden>Choose your Region</option>');
        regions.forEach(function(region) {
            $("#update-regionSelect").append(new Option(region.name, region.id));
        });
    },
    error: function(xhr, status, error) {
        console.error('Error fetching regions:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error!',
            text: 'Failed to load regions.'
        });
    }
});

// Handle opening the update modal and populating data
function openUpdateModal(countryId) {
    $.ajax({
        url: '/api/country/' + countryId, // Endpoint untuk mengambil data negara berdasarkan ID
        method: 'GET',
        success: function(country) {
            // Populate the modal form with country data
            $("#update-name").val(country.name);
            $("#update-code").val(country.code);
            $("#update-regionSelect").val(country.region.id);
            $("#update-id").val(country.id); // Set the hidden ID field
            $('#update').modal('show'); // Show the modal
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'Failed to fetch country data.'
            });
        }
    });
}

// Handle the update operation when the "Simpan" button is clicked
$("#update-country").click(function() {
    const countryId = $("#update-id").val();
    const countryName = $("#update-name").val().trim();
    const countryCode = $("#update-code").val().trim();
    const regionId = $("#update-regionSelect").val();

    if (countryName && countryCode && regionId) {
        $.ajax({
            url: '/api/country/' + countryId, // Endpoint untuk melakukan update
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                name: countryName,
                code: countryCode,
                region: { id: regionId }
            }),
            success: function() {
                $('#update').modal('hide'); // Close the modal
                Swal.fire({
                    icon: 'success',
                    title: 'Updated!',
                    text: 'The country has been updated successfully!'
                }).then(() => {
                    location.reload(); // Refresh halaman
                });
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'There was an error updating the country.'
                });
                console.error('Error updating country:', error);
            }
        });
    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Warning!',
            text: 'Please fill in all fields.'
        });
    }
});



    $("#update-country").click((event) => {
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
                url: `/api/country/${valueId}`,
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(updateData),
                success: (res) => {
                    $("#update").modal("hide");
                    $("#tb-country").DataTable().ajax.reload();
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
});



function findById(id) {
    $.ajax({
        method: "GET",
        url: `/api/country/${id}`,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#detail-id").val(res.id);
            $("#detail-name").val(res.name);
            $("#detail-code").val(res.code);
            $("#detail-region").val(res.region.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: `/api/country/${id}`,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
            $("#update-id").val(res.id);
            $("#update-name").val(res.name);
            $("#update-code").val(res.code);
            $("#update-region").val(res.region.name);
        },
        error: (err) => {
            console.log(err);
        },
    });
}

function deleteCountry(id) {
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
                // Memulai permintaan AJAX untuk menghapus data
                $.ajax({
                    method: "DELETE",
                    url: `/api/country/${id}`,
                    dataType: "json",
                    contentType: "application/json",
                    success: (res) => {
                        $("#tb-country").DataTable().ajax.reload();
                        swalWithBootstrapButtons.fire({
                            title: "Terhapus",
                            text: "Data country telah terhapus",
                            icon: "success",
                        }).then(() => {
                            location.reload(); // Refresh halaman
                        });
                    },
                    error: (err) => {
                        // Menampilkan pesan error jika terjadi kesalahan
                        console.error("Error deleting country:", err);
                        swalWithBootstrapButtons.fire({
                            title: "Gagal",
                            text: "Terjadi kesalahan saat menghapus data.",
                            icon: "error",
                        });
                    },
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "Batal",
                    text: "Country batal untuk dihapus",
                    icon: "error",
                });
            }
        });
}
