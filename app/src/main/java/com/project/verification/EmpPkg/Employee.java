package com.project.verification.EmpPkg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.verification.CriminalRecordStored;
import com.project.verification.R;
import com.project.verification.SubmittedResponse;
import com.project.verification.securityRegistration.Emp_User;
import com.project.verification.securityRegistration.securityLogin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Employee extends AppCompatActivity
{
    Button upload;
    //TextInputEditText submited;//, criminalrecord;
    TextView inEdName, inEdAge,submited,selected,status;//,inEdSalary;

    CardView adharCard,pan,d_licen,photo,criminal_card,status_card;//;,criminal;

    private ImageView photo1,photo2,photo3,photo4;//,photo5;

    ImageView adharPicStatus,panPicsStatus,diverPicStatus,photoPicStatus;

    private Bitmap adharimg,panimg,licenimg,ephoto;//,criminalimg;

    private StorageReference mStorageRef;
    //Upload Video
   // private ActionBar actionBar;
    private VideoView videoView;

    private FloatingActionButton pickVideoFab;

    private static final int VIDEO_PICK_GALLARY_CODE = 100;
    private static final int VIDEO_PICK_CAMERA_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;



    private String[] cameraPermissions;
    private Uri videoUri = null; //

    private String title;

    private ProgressDialog progressDialog;

    CriminalPojo criminalPojo = new CriminalPojo();

//    FirebaseDatabase database;
//    DatabaseReference reff;
//    //Spinner spinner;
//    int maxid = 0;

    //Criminal Record
    private EditText titlee;//, description, author;
    private Button save;

    FirebaseDatabase database;
    //DatabaseReference reff;
    int maxid = 0;

    Spinner spin1;
    EditText ed1,type_status;

    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        spin1 = findViewById(R.id.spinner);
        ed1 = findViewById(R.id.title);
        type_status = findViewById(R.id.type_status);
       // saveStatus = findViewById(R.id.saveStatus);

        photo1 = findViewById(R.id.adharimg);
        photo2 = findViewById(R.id.panimg);
        photo3 = findViewById(R.id.licenimg);
        photo4 = findViewById(R.id.ephoto);
        // photo5 = findViewById(R.id.criminalimg);
        upload = findViewById(R.id.upload);

        inEdName = findViewById(R.id.tvName);
        inEdAge = findViewById(R.id.tvAge);
        //inEdSalary = findViewById(R.id.tvSalary);
        submited = findViewById(R.id.submited);
      //  criminalrecord = findViewById(R.id.criminalrecord);

        //CardView
        adharCard = findViewById(R.id.adharCard);
        pan = findViewById(R.id.pan);
       d_licen = findViewById(R.id.d_licen);
        photo = findViewById(R.id.photo);
        selected = findViewById(R.id.selected);
        status = findViewById(R.id.status);
        criminal_card = findViewById(R.id.criminal_card);
        status_card = findViewById(R.id.status_card);
       // selectedd = findViewById(R.id.select_status);

        //panPicsStatus,diverPicStatus,photoPicStatus;
        adharPicStatus = findViewById(R.id.adharimg_ok);
        panPicsStatus = findViewById(R.id.pan_ok);
        diverPicStatus = findViewById(R.id.driver_ok);
        photoPicStatus = findViewById(R.id.photo_ok);
        //criminal = findViewById(R.id.criminal);


        //  String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
      //  String salary = getIntent().getStringExtra("salary");

//        Log.d("ID",id);
//        ab.setTitle(name);

        inEdName.setText(name);
        inEdAge.setText(age);
        // inEdSalary.setText(salary);

     //   actionBar = getSupportActionBar();

        /*if (actionBar != null)
        {
            actionBar.setTitle("Add New Video");
        }*/

     //   actionBar.setDisplayShowHomeEnabled(true);
     //   actionBar.setDisplayHomeAsUpEnabled(true);

        videoView = findViewById(R.id.videoView);
        pickVideoFab = findViewById(R.id.pickVideoFab);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Uploading Doucuments!");
        progressDialog.setCanceledOnTouchOutside(false);

       // spinner = findViewById(R.id.spinner);
       // reff = database.getInstance().getReference().child("Employee_Master");

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        Member member = new Member();

//        List<String> categories = new ArrayList<>();
//        categories.add(0, "Choose Record");
//        categories.add("Criminal Record Found");
//        categories.add("Criminal Record Not Found");
//        ArrayAdapter<String> dataAdapter;
//        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // spinner.setAdapter(dataAdapter);

        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin1.setVisibility(View.VISIBLE);
                ed1.setVisibility(View.VISIBLE);
//                Intent intent = new Intent(Employee.this, CriminalRecordStored.class);
//                startActivity(intent);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  type_status.setVisibility(View.VISIBLE);
//                Intent intent = new Intent(Employee.this, CriminalRecordStored.class);
//                startActivity(intent);
            }
        });


//        selectedd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveStatus.setVisibility(View.VISIBLE);
////                Intent intent = new Intent(Employee.this, CriminalRecordStored.class);
////                startActivity(intent);
//            }
//        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (adapterView.getItemAtPosition(i).equals("choose event")) {
//
//                } else {
//                    String item = adapterView.getItemAtPosition(i).toString();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists())
//                    maxid = (int) dataSnapshot.getChildrenCount();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        adharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 0);
            }
        });

        pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1);
            }
        });

        d_licen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 2);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 3);
            }
        });

//        criminal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(camera, 4);
//            }
//        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // uploadd();
                submitData();

                if (videoUri == null) {
                    Toast.makeText(Employee.this, "Pick a Video before you can upload", Toast.LENGTH_SHORT).show();
                } else {
                    uploadVideoFirbase();
                }
            }

            private void submitData() {
                //TextInputEditText inEdName,inEdAge,inEdSalary,submited,criminalrecord;
                //reff.child(String.valueOf(maxid + 1)).setValue(member);// Add Criminal Record

                HashMap<String, Object> map = new HashMap<>();
                map.put("inEdName", inEdName.getText().toString());
                map.put("inEdAge", inEdAge.getText().toString());
                map.put("title", ed1.getText().toString());
                map.put("status", type_status.getText().toString());

                map.put("spinner",spin1.getSelectedItem().toString());

                //map.put("inEdSalary",inEdSalary.getText().toString());
               // map.put("submited", submited.getText().toString());
               // map.put("criminalrecord", criminalrecord.getText().toString());
               // map.put("spinner",spinner.getSelectedItem().toString());

                FirebaseDatabase.getInstance().getReference().child("Employee_Master").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("ugugug", "onComplete");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("ugugug", "onComplete" + e.toString());
                    }
                });
            }
        });

        pickVideoFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                videoPickDialog();
            }
        });

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void uploadVideoFirbase()
    {
        //Show Progress
        progressDialog.show();

        //timestamp
        String timestamp = "" + System.currentTimeMillis();

        //file path and name in firebase
        String filePathAndName = "Videos/" + "video_" + timestamp;

        //storage reference
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathAndName);

        //upload video, you can upload any type of fie using this method
        storageReference.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //video uploaded, get url of uploaded video
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        Uri downloadUri = uriTask.getResult();
                        if (uriTask.isSuccessful()) {
                            //url is uploaded video is received

                            //now we can add video details to our firebase database
                            HashMap<String, Object> hashMap = new HashMap<>();
                            // hashMap.put("title",title);
                            hashMap.put("id", "" + timestamp);
                            hashMap.put("timestamp", "" + timestamp);
                            hashMap.put("videoUrl", "" + downloadUri);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Videos");
                            reference.child(timestamp)
                                    .setValue(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //Video details added to dbase
                                            progressDialog.dismiss();
                                            Toast.makeText(Employee.this, "Uploading Successfully!!!!!!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Employee.this, SubmittedResponse.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    //failed Adding details to dbase
                                    progressDialog.dismiss();
                                    Toast.makeText(Employee.this, "Uploading Not Successfully!!!!!!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                //Failed Uploading to dbase
                progressDialog.dismiss();
                Toast.makeText(Employee.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void videoPickDialog() {
        //option to Display to Dialog
        String[] options = {"Camera"};

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Video From")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //camera Clicked
                            if (!checkCameraPermission()) {
                                //camera permission not allowed, request it
                                requestCameraPermission();
                            } else {
                                //permission already allowed ,take picture
                                videoPickCamera();
                            }
                        } else if (i == 1) {
                            //gallery Clicked
                        }
                    }
                }).show();
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED;

        return result1 && result2;
    }

    //video Pick gallary
    private void videoPickGallary() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), VIDEO_PICK_GALLARY_CODE);
    }

    //video Pick Camera
    private void videoPickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//ACTION_IMAGE_CAPTURE  ACTION_VIDEO_CAPTURE
        startActivityForResult(intent, VIDEO_PICK_CAMERA_CODE);
    }

    private void setVideoToVideoView() {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.setVideoURI(videoUri);
        videoView.requestFocus();

        adharCard.setVisibility(View.VISIBLE);
        pan.setVisibility(View.VISIBLE);
        d_licen.setVisibility(View.VISIBLE);
        photo.setVisibility(View.VISIBLE);
        criminal_card.setVisibility(View.VISIBLE);
        status_card.setVisibility(View.VISIBLE);
        upload.setVisibility(View.VISIBLE);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //check permission allowed or not
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        //both permission allowed
                        videoPickCamera();
                    } else {
                        Toast.makeText(this, "Camera & Storage permission are required", Toast.LENGTH_SHORT).show();
                    }
                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //go to previous Activity

        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == VIDEO_PICK_GALLARY_CODE) {
                videoUri = data.getData();
                setVideoToVideoView();
            } else if (requestCode == VIDEO_PICK_CAMERA_CODE) {
                videoUri = data.getData();
                setVideoToVideoView();
            }
        }

        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            adharimg = (Bitmap) data.getExtras().get("data");
            photo1.setImageBitmap(adharimg);
            uploadd();
        }

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            panimg = (Bitmap) data.getExtras().get("data");
            photo2.setImageBitmap(panimg);
            uploaddd();
        }

        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            licenimg = (Bitmap) data.getExtras().get("data");
            photo3.setImageBitmap(licenimg);
            uploadddd();
        }

        if (requestCode == 3 && resultCode == RESULT_OK)
        {
            ephoto = (Bitmap) data.getExtras().get("data");
            photo4.setImageBitmap(ephoto);
            uploaddddd();
        }

//        if (requestCode == 4 && resultCode == RESULT_OK)
//        {
//            criminalimg = (Bitmap) data.getExtras().get("data");
//            photo5.setImageBitmap(criminalimg);
//        }

    }

    private void uploadd() {

        //final ProgressBar p = findViewById(R.id.progressbar);

       // p.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        adharimg.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        final String random = UUID.randomUUID().toString();
        StorageReference imageRef = mStorageRef.child("image/" + random);

        byte[] b = stream.toByteArray();

        imageRef.putBytes(b)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       // p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                            }
                        });
                        adharPicStatus.setImageResource(R.drawable.ic_baseline_done_outline_24);
                        Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Photo Uploaded Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        adharPicStatus.setImageResource(R.drawable.ic_baseline_close_24);
                       // Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });









//        ByteArrayOutputStream streammmmm = new ByteArrayOutputStream();
//        ephoto.compress(Bitmap.CompressFormat.JPEG, 100, streammmmm);
//
//        final String randommmmm = UUID.randomUUID().toString();
//        StorageReference imageRefffff = mStorageRef.child("image/" + randommmmm);
//
//        byte[] r = streamm.toByteArray();
//
//        imageRefffff.putBytes(r)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        p.setVisibility(View.GONE);
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                Uri downloadUri = uri;
//                            }
//                        });
//                        //  Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Photo Uploaded Successfully!!!", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        p.setVisibility(View.GONE);
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//            // Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Uploading Failed!!!", Toast.LENGTH_SHORT).show();
//          }
//      });
    }

    private void uploaddd()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream streamm = new ByteArrayOutputStream();
        panimg.compress(Bitmap.CompressFormat.JPEG, 100, streamm);

        final String randomm = UUID.randomUUID().toString();
        StorageReference imageReff = mStorageRef.child("image/" + randomm);

        byte[] c = streamm.toByteArray();

        imageReff.putBytes(c)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                            }
                        });// panPicsStatus = findViewById(R.id.pan_ok);
                        //diverPicStatus = findViewById(R.id.driver_ok);
                       // photoPicStatus = findViewById(R.id.photo_ok);
                        panPicsStatus.setImageResource(R.drawable.ic_baseline_done_outline_24);
                        Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Photo Uploaded Successfully!!!", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), SubmittedResponse.class);
                        //startActivity(intent);
                        //finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //  p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Uploading Failed!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadddd()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream streammm = new ByteArrayOutputStream();
        licenimg.compress(Bitmap.CompressFormat.JPEG, 100, streammm);

        final String randommm = UUID.randomUUID().toString();
        StorageReference imageRefff = mStorageRef.child("image/" + randommm);

        byte[] l = streammm.toByteArray();

        imageRefff.putBytes(l)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      //  p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                            }
                        });

                        // = findViewById(R.id.driver_ok);
                        // photoPicStatus = findViewById(R.id.photo_ok);
                        diverPicStatus.setImageResource(R.drawable.ic_baseline_done_outline_24);
                        Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Photo Uploaded Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                       // Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Uploading Failed!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploaddddd()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream streammmm = new ByteArrayOutputStream();
        ephoto.compress(Bitmap.CompressFormat.JPEG, 100, streammmm);

        final String randommmm = UUID.randomUUID().toString();
        StorageReference imageReffff = mStorageRef.child("image/" + randommmm);

        byte[] e = streammmm.toByteArray();

        imageReffff.putBytes(e)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      //  p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                            }
                        });
                        //  = findViewById(R.id.photo_ok);
                          photoPicStatus.setImageResource(R.drawable.ic_baseline_done_outline_24);
                          Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Photo Uploaded Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        // Toast.makeText(com.project.verification.EmpPkg.Employee.this, "Uploading Failed!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
